package io.github.thurstonsand.website

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._

object Main extends IOApp {
  def run(args: List[String]) =
    WebsiteServer.stream[IO].compile.drain.as(ExitCode.Success)
}