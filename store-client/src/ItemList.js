import { Link } from "react-router-dom";
import Item from "./Item";
import SampleItems from "./sample-data/products.json";
import { useEffect, useState } from "react";
import { getProducts } from "./API";
import { toast } from "react-toastify";
import { useSelector } from "react-redux";

function ItemList() {
  // const [data, setData] = useState([...SampleItems]);
  const [data, setData] = useState([{}, {}, {}, {}]);
  const data2 = useSelector((state) => state.cart.products);
  useEffect(() => {
    console.log(data2);
  }, [data2]);
  useEffect(() => {
    getProducts()
      .then((response) => {
        if (response.data) {
          setData(response.data);
        } else {
          throw new Error("error loading data");
        }
      })
      .catch((e) => {
        console.error(e);
        toast.error("Error loading data, refresh to retry", {
          position: "top-center",
          autoClose: 5000,
          hideProgressBar: true,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
        });
      });
  }, []);

  return (
    <div className={"xl:mx-auto xl:container h-full"}>
      {(() => {
        const arr = [];
        data.forEach((product) => {
          arr.push(
            <div className={"mb-10 mt-5"}>
              <Item {...product} />
            </div>
          );
        });
        return arr;
      })()}
    </div>
  );
}

export default ItemList;
