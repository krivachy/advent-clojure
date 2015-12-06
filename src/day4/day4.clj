(ns day4.day4
  (:require [digest :as digest]))

(def input (slurp "day4.in"))

(defn good-hash? [number matching-prefix]
  (.startsWith (digest/md5 (str input number)) matching-prefix))

(defn positive-numbers
  ([] (positive-numbers 1))
  ([n] (lazy-seq (cons n (positive-numbers (inc n))))))


(defn search-hashes [starting-number matching-prefix]
  (loop [number starting-number]
    (if (good-hash? number matching-prefix)
      number
      (recur (inc number)))))

; Solution part 1
(println (search-hashes 1 "00000"))

; Solution part 2
(println (search-hashes 1 "000000"))