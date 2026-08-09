# Design Notes

## Why ArrayList instead of Array?

I used `ArrayList` (instead of a plain array) in all three repositories (`StudentRepository`, `CourseRepository`, `EnrollmentRepository`) mainly because I don't know in advance how many students, courses, or enrollments will be added at runtime. A plain array needs a fixed size decided upfront, so if I picked something like `Student[10]` and the tenth student got added, the array would be full and I'd have to manually create a bigger array and copy everything over just to add one more student.

`ArrayList` handles all of that automatically — it grows dynamically as items are added, so I can just call `.add()` without worrying about running out of space. It also comes with built-in methods like `.remove()` and iteration support (used in the `for` loops in the service and `Main` classes), which would have to be written manually if I used a raw array. Since this project is meant to represent something closer to a real system with an unpredictable number of records, `ArrayList` was the natural fit.

## Where I Used Static Members and Why

I used `static` in two places:

1. **`IdGenerator`** — the counters (`studentIdCounter`, `courseIdCounter`, `enrollmentIdCounter`) are `private static int` fields, and the ID-generating methods (`getNextStudentId()`, etc.) are `static` methods. This made sense because I need **one single, shared counter** across the entire application — not a separate counter per object. If these weren't static, every new `IdGenerator` object would start its own counter back at 0, which would cause duplicate IDs. Making them static means there's only ever one copy in memory, shared by every part of the program that calls `IdGenerator.getNextStudentId()`, and it can be called directly on the class without ever creating an `IdGenerator` object.

2. **`AppConstants` and `MenuOptions`** — these use `public static final` fields (e.g. `MenuOptions.ADD_STUDENT`, `AppConstants.DIVIDER`). `static` here means I can reference them directly through the class name from anywhere (like `Main.java`) without instantiating anything, and `final` locks them so they can never be accidentally reassigned somewhere else in the code. This also avoids "magic numbers" scattered through `Main.java` — instead of `if (choice == 1)`, the code reads `if (choice == MenuOptions.ADD_STUDENT)`, which is much clearer.

## Where I Used Inheritance and What I Gained

I used inheritance with `Student extends Person`. `Person` is the base class holding common fields shared by any person in the system — `id`, `firstName`, `lastName`, `email` — along with a `getDisplayName()` method. `Student` extends `Person` and adds its own extra fields (`batch`, `active`) on top, while reusing everything `Person` already defines through `super(...)` in its constructor instead of rewriting the same four fields and getters/setters again.

`Student` also overrides `getDisplayName()` to return a more specific version (`"Student: " + firstName + " " + lastName`) instead of the generic one in `Person` — demonstrating basic polymorphism, since calling `getDisplayName()` on a `Student` object runs `Student`'s version, not `Person`'s.

What I actually gained from this: less duplicated code (name/id/email logic lives in exactly one place), and a structure that's easy to extend later — if the project ever needed a `Trainer` class, it could also extend `Person` and reuse the same base fields without touching `Student` at all.