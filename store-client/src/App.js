import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import ItemList from "./ItemList";
import CheckOut from "./Checkout";
import { ToastContainer } from "react-toastify";
import { useSelector } from "react-redux";
import _ from "underscore";

function App() {
  const orderCount = useSelector((state) => _.keys(state.cart.products).length);

  return (
    <Router>
      <div className="App flex flex-col w-screen h-screen">
        <ToastContainer
          position="top-center"
          autoClose={5000}
          hideProgressBar
          newestOnTop={false}
          closeOnClick
          rtl={false}
          pauseOnFocusLoss
          draggable
          pauseOnHover
        />
        <div className={"w-full h-12 bg-black flex flex-row-reverse px-3"}>
          <Link to={"/check-out"} className={"h-8 my-auto p-0"}>
            <button
              className={"rounded-md h-full px-2 font-bold bg-yellow-500"}
            >
              Check Out ({orderCount})
            </button>
          </Link>
        </div>
        <Switch>
          <Route path={"/check-out"}>
            <CheckOut />
          </Route>
          <Route path={"/"}>
            <ItemList />
          </Route>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
