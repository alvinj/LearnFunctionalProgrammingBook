# ZIO Notes

Here are a few more ZIO notes from the official [ZIO “creating effects” documentation](https://zio.dev/overview/overview_creating_effects). Please see that documentation for *many* more examples.


## ZIO succeed, attempt, and fail

Use `succeed` when you’re sure an effect will succeed, `attempt` if you don’t know it will succeed, and `fail` when you know it fails:

```scala
val s  = ZIO.succeed(42)
val a  = ZIO.attempt(StdIn.readLine())
val f1 = ZIO.fail("Uh oh!")
val f2 = ZIO.fail(Exception("Uh oh!"))
```


## Create a ZIO from an Option

The ZIO documentation includes these two `Option`-related examples:

```scala
val zoption: IO[Option[Nothing], Int] =
    ZIO.fromOption(Some(2))

val zoption2: ZIO[Any, String, Int] =
    zoption.orElseFail("It wasn’t there!")
```


## Create a ZIO from an Either

This shows how to create a `ZIO` from an `Either`:

```scala
val zeither: ZIO[Any, Nothing, String] =
    ZIO.fromEither(Right("Success!"))
```


## Create a ZIO from a Try

This shows how to create a `ZIO` from a `Try`:

```scala
import scala.util.Try
val ztry = ZIO.fromTry(Try(42 / 0))
```








