(ns om-material-ui.core
  (:require [clojure.string :as str]
            [om-tools.dom :as omt]))

(defn kebab-case
  "Converts CamelCase / camelCase to kebab-case"
  [s]
  (str/join "-" (map str/lower-case (re-seq #"\w[a-z]+" s))))

(def material-tags
  '[AppBar
  AppCanvas
  Circle
  Checkbox
  DatePicker
  DialogWindow
  Dialog
  DropDownIcon
  DropDownMenu
  EnhancedButton
  FlatButton
  FloatingActionButton
  FocusRipple
  FontIcon
  IconButton
  Icon
  InkBar
  Input
  LeftNav
  MenuItem
  Menu
  Overlay
  Paper
  RadioButton
  RadioButtonGroup
  RaisedButton
  Slider
  SlideIn
  Snackbar
  SvgIcon
  Tab
  TabTemplate
  Tabs
  TableHeader
  TableRowsItem
  TableRows
  TextField
  TimePicker
  Toggle
  ToolbarGroup
  Toolbar
  Tooltip
  TouchRipple])


#+clj
(defn ^:private gen-bootstrap-inline-fn [tag]
  `(defmacro ~(symbol (kebab-case (str tag)))
     [opts# & children#]
     (let [ctor# '(js/React.createFactory ~(symbol "js" (str "MaterialUI." (name tag))))]
       (if (om-tools.dom/literal? opts#)
         (let [[opts# children#] (om-tools.dom/element-args opts# children#)]
           (cond
            (every? (complement om-tools.dom/possible-coll?) children#)
            `(~ctor# ~opts# ~@children#)

            (and (= (count children#) 1) (vector? (first children#)))
            `(~ctor# ~opts# ~@(-> children# first flatten))

            :else
            `(apply ~ctor# ~opts# (flatten (vector ~@children#)))))
         `(om-tools.dom/element ~ctor# ~opts# (vector ~@children#))))))

(defmacro ^:private gen-bootstrap-inline-fns []
  `(do ~@(clojure.core/map gen-bootstrap-inline-fn material-tags)))

#+clj
(gen-bootstrap-inline-fns)

