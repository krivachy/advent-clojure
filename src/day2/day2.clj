(ns day2.day2)

(require '[clojure.string :as str])

; CALCUATIONS

(defn surface-area [l w h]
  ; 2*l*w + 2*w*h + 2*h*l
  (+ (* 2 l w) (* 2 w h) (* 2 h l)))

(defn extra-slack [l w h]
  ; extra slack: the area of the smallest side
  (min (* l w) (* w h) (* h l)))

(defn wrapping-paper-sqft [length width height]
  ; use let for funnsies
  (let [l length
        w width
        h height]
    (+ (surface-area l w h) (extra-slack l w h))))

; INPUT

(def input
  (slurp "day2.in"))

(def input-rows
  (clojure.string/split-lines input))

(defn parse-dimensions [string-dimensions]
  (map read-string (str/split string-dimensions #"x")))

(def dimensions
  (map parse-dimensions input-rows))

(def total-wrapping-paper
  (reduce + (map #(apply wrapping-paper-sqft %) dimensions)))

; part 1 solution:
(println total-wrapping-paper)
