(ns day4.day4
  (:require [digest :as digest]))

(def input (slurp "day4.in"))

(defn five-leading-zeros? [string]
  (.startsWith string "00000"))

(defn good-hash? [number]
  (five-leading-zeros? (digest/md5 (str input number))))

(defn positive-numbers
  ([] (positive-numbers 1))
  ([n] (lazy-seq (cons n (positive-numbers (inc n))))))


(defn search-hashes [starting-number]
  (loop [number starting-number]
    (if (good-hash? number)
      number
      (recur (inc number)))))

(def result (search-hashes 1))

; Solution part 1
(println result)