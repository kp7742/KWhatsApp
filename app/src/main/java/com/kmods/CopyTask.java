package com.kmods;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyTask extends AsyncTask<File, Integer, Integer> {
    Context ctx;
    ProgressDialog dialog;
    boolean done;
    int i;
    boolean isBackup;
    int max;
    File sourceLocation;
    String str;
    File targetLocation;

    public CopyTask(Context context, boolean z, File file, File file2) {
        this.i = 0;
        this.done = false;
        this.isBackup = false;
        this.max = 0;
        this.ctx = context;
        this.isBackup = z;
        if (z) {
            this.str = "Backup";
        } else {
            this.str = "Restore";
        }
        this.dialog = new ProgressDialog(context);
        this.dialog.setProgressStyle(1);
        this.dialog.setCancelable(false);
        this.dialog.setIndeterminate(false);
        this.dialog.setTitle(this.str);
        this.dialog.setMessage("Proccess Data...");
        this.sourceLocation = file;
        this.targetLocation = file2;
    }

    void CountFiles(File file) {
        if (file.isDirectory()) {
            for (File CountFiles : file.listFiles()) {
                CountFiles(CountFiles);
            }
        }
        this.max++;
    }

    void DeleteDirectory(File file) {
        if (file.isDirectory()) {
            for (File DeleteDirectory : file.listFiles()) {
                DeleteDirectory(DeleteDirectory);
            }
        }
        file.delete();
    }

    public void copyDirectory(File file, File file2) throws IOException {
        int i = 0;
        file.getPath();
        if (file.isDirectory()) {
            if (file2.exists()) {
                DeleteDirectory(file2);
                file2.mkdir();
            } else {
                file2.mkdir();
            }
            String[] list = file.list();
            while (i < list.length) {
                copyDirectory(new File(file, list[i]), new File(file2, list[i]));
                i++;
            }
            return;
        }
        InputStream fileInputStream = new FileInputStream(file);
        OutputStream fileOutputStream = new FileOutputStream(file2);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read <= 0) {
                fileInputStream.close();
                fileOutputStream.close();
                this.i++;
                publishProgress(new Integer[]{Integer.valueOf(this.i)});
                return;
            }
            fileOutputStream.write(bArr, 0, read);
        }
    }

    protected Integer doInBackground(File... fileArr) {
        try {
            copyDirectory(this.sourceLocation, this.targetLocation);
            this.done = true;
        } catch (IOException e) {
            e.printStackTrace();
            this.done = false;
        }
        return null;
    }

    protected void onCancelled() {
        Toast.makeText(this.ctx, this.str + "Fail", Toast.LENGTH_SHORT).show();
        if (this.dialog.isShowing()) {
            this.dialog.dismiss();
        }
    }

    protected void onPostExecute(Integer num) {
        this.dialog.dismiss();
        if (this.done) {
            Toast.makeText(this.ctx, this.str + "Done", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this.ctx, this.str + "Fail", Toast.LENGTH_SHORT).show();
        }
        if (!this.isBackup) {
            System.exit(0);
        }
    }

    protected void onPreExecute() {
        try {
            CountFiles(this.sourceLocation);
            this.dialog.setMax(this.max);
            this.dialog.show();
        } catch (Exception e) {
            cancel(true);
        }
    }

    protected void onProgressUpdate(Integer... numArr) {
        this.dialog.setProgress(numArr[0].intValue());
    }
}
