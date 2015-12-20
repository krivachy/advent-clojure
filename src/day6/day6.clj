(ns day6.day6
  (:require [clojure.string :as str]))

(def input (slurp "day6.in"))

(def input-lines (str/split-lines input))

(defn create-empty-grid [] (make-array Boolean/TYPE 1000 1000))

(defn mutate-grid! [grid x y value]
  ; mutate grid
  (aset (aget grid x) y value)
  ; return with grid
  grid)

(defn toggle-grid! [grid x y]
  (let [value (not (nth (nth grid x) y))]
    (mutate-grid! grid x y value)))

(defn action-on-grid! [grid fn fromX fromY toX toY]
  (for [x (range fromX (inc toX))
        y (range fromY (inc toY))]
    (fn grid x y)))

(defn turn-on-one! [grid x y] (mutate-grid! grid x y true))
(defn turn-off-one! [grid x y] (mutate-grid! grid x y false))

(defn toggle-range! [grid fromX fromY toX toY] (action-on-grid! grid toggle-grid! fromX fromY toX toY))
(defn turn-on-range! [grid fromX fromY toX toY] (action-on-grid! grid turn-on-one! fromX fromY toX toY))
(defn turn-off-range! [grid fromX fromY toX toY] (action-on-grid! grid turn-off-one! fromX fromY toX toY))

(defn command-function! [command-words args]
  (do
    ; (pprint (nth args 1))
    (println (str "matching " command-words))
    (if (= (count command-words) 5)
      (do
        ; (println (str "matching " command-words))
        (if (= (do
                 ;(println (str "1 " (nth command-words 1)))
                 (nth command-words 1)) "on") (apply turn-on-range! args) (apply turn-off-range! args)))
      (apply toggle-range! args))))

(defn command-arguments [command-line]
  (map read-string (re-seq #"\d+" command-line)))

(defn split-commands [line] (str/split line #"\s"))

(defn run-command! [grid line]
  (command-function! (split-commands line) (cons grid (command-arguments line))))

(defn count-result [grid]
  (reduce + 0 (map (fn [arr] (count (filter identity (vec arr)))) grid)))

(def final-grid (reduce #(run-command! %1 %2) (create-empty-grid) input-lines))

(println (count-result final-grid))
