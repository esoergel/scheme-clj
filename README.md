# scheme->clj

This is a very basic toy project to figure out an interpreter.  I've been
going through [SICP](https://sarabander.github.io/sicp/html/index.xhtml), and
we do a lot of this stuff in Chapter 4, but nothing that was really end-to-end.

This currently will let me type in scheme code (as a string right now, but it
could trivially be read from a .scm file), then run it in a clojure
interpreter.  (Scheme for the JVM, baby!)

## Issues/TODOs
I doubt I'll get to this, but anyways:

 * Support a larger subset of scheme - currently only function and variable
   bindings are supported, everything else just uses clojure directly.
 * Split up expressions by parens - currently I'm just doing one expression
   per line.
 * Provide explicit mappings or raise an exception - right now if an
   expression isn't recognized, it's passed through with the assumption that
   it's valid clojure, which is useful for primitives like (+ 2 1) so I
   don't need to map them.  I'd like to force this to be explicit, even if
   I'm just using the clojure implementation anyways.

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
