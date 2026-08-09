# JVM Basics

## JDK vs JRE vs JVM

- **JDK (Java Development Kit)** — The complete toolkit for developers. Includes the compiler (`javac`), debugging tools, and the JRE bundled inside it. Needed to *write and compile* Java code.
- **JRE (Java Runtime Environment)** — A subset of the JDK needed only to *run* already-compiled Java programs. Includes the JVM plus core Java libraries (`java.lang`, `java.util`, etc.). Does not include `javac`, so it cannot compile `.java` source files — only run existing `.class` files.
- **JVM (Java Virtual Machine)** — The innermost component. It does not compile code — that's `javac`'s job. Instead, the JVM **executes** the compiled bytecode (`.class` files), translating it into instructions the actual operating system and CPU can run.

**Relationship: The JDK contains the JRE, and the JRE contains the JVM.

## What is Bytecode?

- When `javac` compiles a `.java` file, it does **not** produce native machine code directly.
- It produces a `.class` file containing **bytecode** — an intermediate, platform-independent instruction format.
- Bytecode is not human-readable Java source, and it's not raw CPU-specific machine code either — it sits in between.
- The JVM is what reads this bytecode and executes it on the actual machine.

## What Does "Write Once, Run Anywhere" Mean?

- Since `javac` compiles Java source into universal bytecode (not OS-specific machine code), the same `.class` file can run unmodified on Windows, Linux, or macOS.
- The only requirement is that each platform has its own compatible JVM installed — the JVM is the platform-specific piece that adapts bytecode to the actual OS/hardware underneath.
- This means a developer compiles their code **once**, and it can run **anywhere** a JVM is available, without needing to recompile per platform.