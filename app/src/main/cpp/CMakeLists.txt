cmake_minimum_required(VERSION 3.4.1)

set(CMAKE_CXX_STANDARD 14)

set(LIBRARY_NAME "whale")

enable_language(ASM)
SET(CMAKE_ASM_FLAGS "${CFLAGS}  -x assembler-with-cpp")

if (CMAKE_SYSTEM_NAME MATCHES "^Android")
    set(KERNEL "Linux")
    set(PLATFORM "Android")
    set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -fvisibility=hidden")
    set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fvisibility=hidden")
    set(CMAKE_SHARED_LINKER_FLAGS "${CMAKE_SHARED_LINKER_FLAGS} -s -Wl,--gc-sections")
    add_definitions("-DWHALE_ANDROID_AUTO_LOAD")
    message("Building Whale for Android(${CMAKE_SYSTEM_PROCESSOR})...")
endif ()


include_directories(whale/libffi whale/libffi/platform_include)

set(WHALE_SOURCES
        whale/whale.cc
        whale/interceptor.cc
        whale/dbi/hook_common.cc
        whale/platform/memory.cc
        whale/assembler/assembler.cc
        whale/assembler/memory_region.cc
        whale/dbi/instruction_set.cc
        whale/libffi/closures.c
        whale/libffi/debug.c
        whale/libffi/dlmalloc.c
        whale/libffi/java_raw_api.c
        whale/libffi/prep_cif.c
        whale/libffi/raw_api.c
        whale/libffi/types.c
        whale/libffi/ffi_cxx.cc
        )

set(WHALE_LINUX_SOURCES
        whale/platform/linux/elf_image.cc
        whale/platform/linux/process_map.cc
        )


set(WHALE_DARWIN_SOURCES
        whale/dbi/darwin/macho_import_hook.cc
        )

set(WHALE_ANDROID_ART
        whale/android/art/native_on_load.cc
        whale/android/art/art_runtime.cc
        whale/android/art/art_symbol_resolver.cc
        whale/android/art/java_types.cc
        whale/android/art/well_known_classes.cc
        whale/android/art/art_method.cc
        whale/android/art/scoped_thread_state_change.cc
        whale/android/art/art_jni_trampoline.cc
        )

set(WHALE_AARCH32
        whale/dbi/arm/decoder_arm.cc
        whale/dbi/arm/decoder_thumb.cc
        whale/dbi/arm/inline_hook_arm.cc
        whale/dbi/arm/instruction_rewriter_arm.cc
        whale/libffi/arm/ffi_armv7.c
        whale/libffi/arm/sysv_armv7.S
        )

set(WHALE_AARCH64
        whale/dbi/arm64/decoder_arm64.cc
        whale/dbi/arm64/instruction_rewriter_arm64.cc
        whale/dbi/arm64/inline_hook_arm64.cc
        whale/libffi/aarch64/ffi_arm64.c
        whale/libffi/aarch64/sysv_arm64.S
        )

set(WHALE_DISASSEMBLER_X86_OR_X86_64
        whale/dbi/x86/distorm/decoder.c
        whale/dbi/x86/distorm/distorm.c
        whale/dbi/x86/distorm/instructions.c
        whale/dbi/x86/distorm/insts.c
        whale/dbi/x86/distorm/mnemonics.c
        whale/dbi/x86/distorm/operands.c
        whale/dbi/x86/distorm/prefix.c
        whale/dbi/x86/distorm/textdefs.c
        whale/dbi/x86/distorm/wstring.c
        )

set(WHALE_X86
        whale/assembler/x86/assembler_x86.cc
        whale/assembler/x86/managed_register_x86.cc
        whale/dbi/x86/inline_hook_x86.cc
        whale/dbi/x86/intercept_syscall_x86.cc
        whale/dbi/x86/instruction_rewriter_x86.cc
        whale/libffi/x86/ffi_i386.c
        whale/libffi/x86/sysv_i386.S
        )

set(WHALE_X86_64
        whale/assembler/x86_64/assembler_x86_64.cc
        whale/assembler/x86_64/managed_register_x86_64.cc
        whale/dbi/x86_64/inline_hook_x86_64.cc
        whale/dbi/x86_64/instruction_rewriter_x86_64.cc
        whale/libffi/x86/ffiw64_x86_64.c
        whale/libffi/x86/ffi64_x86_64.c
        whale/libffi/x86/unix64_x86_64.S
        whale/libffi/x86/win64_x86_64.S
        )


if (CMAKE_SYSTEM_PROCESSOR MATCHES "^arm")
    set(CMAKE_ASM_FLAGS "${CMAKE_ASM_FLAGS} -arch armv7")
    set(WHALE_SOURCES ${WHALE_SOURCES} ${WHALE_AARCH32})
elseif (CMAKE_SYSTEM_PROCESSOR MATCHES "^aarch64")
    set(CMAKE_ASM_FLAGS "${CMAKE_ASM_FLAGS} -arch arm64")
    set(WHALE_SOURCES ${WHALE_SOURCES} ${WHALE_AARCH64})
elseif (CMAKE_SYSTEM_PROCESSOR MATCHES "^(i.86|x86?)$")
    set(CMAKE_ASM_FLAGS "${CMAKE_ASM_FLAGS} -arch x86")
    set(WHALE_SOURCES ${WHALE_SOURCES} ${WHALE_X86} ${WHALE_DISASSEMBLER_X86_OR_X86_64})
elseif (CMAKE_SYSTEM_PROCESSOR MATCHES "^x86_64")
    set(CMAKE_ASM_FLAGS "${CMAKE_ASM_FLAGS} -arch x86_64")
    set(WHALE_SOURCES ${WHALE_SOURCES} ${WHALE_X86_64} ${WHALE_DISASSEMBLER_X86_OR_X86_64})
endif ()


if (PLATFORM STREQUAL "Android")
    set(WHALE_SOURCES ${WHALE_SOURCES} ${WHALE_ANDROID_ART})
    if (CMAKE_SYSTEM_PROCESSOR MATCHES "^arm")
        set(WHALE_SOURCES ${WHALE_SOURCES} ${WHALE_ANDROID_ART_AARCH32})
    elseif (CMAKE_SYSTEM_PROCESSOR MATCHES "^aarch64")
        set(WHALE_SOURCES ${WHALE_SOURCES} ${WHALE_ANDROID_ART_AARCH64})
    elseif (CMAKE_SYSTEM_PROCESSOR MATCHES "^(i.86|x86?)$")
        set(WHALE_SOURCES ${WHALE_SOURCES} ${WHALE_ANDROID_ART_X86})
    elseif (CMAKE_SYSTEM_PROCESSOR MATCHES "^x86_64")
        set(WHALE_SOURCES ${WHALE_SOURCES} ${WHALE_ANDROID_ART_X86_64})
    endif ()
endif ()

if (KERNEL STREQUAL "Linux")
    set(WHALE_SOURCES ${WHALE_SOURCES} ${WHALE_LINUX_SOURCES})
endif ()

if (CMAKE_SYSTEM_PROCESSOR MATCHES "^(arm|aarch64)")
    include_directories(whale/assembler/vixl)
endif ()

add_subdirectory(whale/assembler/vixl)
include_directories(whale)

add_definitions("-DWHALE_LIBRARY_NAME=\"lib${LIBRARY_NAME}.so\"")
add_library(${LIBRARY_NAME} SHARED ${WHALE_SOURCES})

target_include_directories(${LIBRARY_NAME} PUBLIC include)

if (CMAKE_SYSTEM_PROCESSOR MATCHES "^(arm|aarch64)")
    target_link_libraries(${LIBRARY_NAME} vixl)
endif ()

if (PLATFORM STREQUAL "Android")
    target_link_libraries(${LIBRARY_NAME} log)
endif ()
