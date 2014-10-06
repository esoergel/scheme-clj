(ns scheme-clj.core
  (:require [clojure.string :as s]
            [clojure.edn :as edn]))

(defn tagged-list? [exp tag]
  (if (list? exp)
    (= (first exp) tag)
    false))

;; lambdas
(defn make-binding [name body]
  (list 'def name body))

(defn make-lambda [params body]
  (list 'fn
        (into [] params)
        body))

;; function definitions
(defn definition? [exp]
  (tagged-list? exp 'define))

(defn definition-variable [exp]
  (let [[_ main body] exp]
    (if (symbol? main)
      main
      (first main))))
(defn definition-value [exp]
  (let [[_ main body] exp]
    (if (symbol? main)
      body
      (make-lambda (rest main) body))))
(defn definition->clj [scheme]
  (make-binding (definition-variable scheme)
                (definition-value scheme)))

;; if stuff
(defn make-if [predicate consequent alt]
  (list 'if predicate consequent alt))
(defn if? [exp] (tagged-list? exp 'if))
(defn if->clj [exp]
  (let [[_ predicate consequent alt] exp]
    (make-if predicate consequent alt)))

;; Actually run it all!

(def scheme "
(define (mult x y) (* x y))
(define (square x) (mult x x))
(if true (square 3) (square 4))
")

(defn interpret-line [line]
  (let [scheme (edn/read-string line)]
    (cond
     (definition? scheme) (definition->clj scheme)
     (if? scheme) (if->clj scheme)
     true scheme)))

(defn eval-line [scheme]
  (let [clj (interpret-line scheme)]
    (do
      (println scheme)
      (println clj) 
      (eval clj))))

(defn eval-scheme [raw]
  (let [lines (s/split-lines (s/trim raw))]
    (last (map eval-line lines))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
