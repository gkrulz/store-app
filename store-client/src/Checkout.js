import SampleItems from "./sample-data/products.json";
import ItemInOrder from "./ItemInOrder";
import { useSelector } from "react-redux";
import _ from "underscore";
import { getOrderTotal } from "./API";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import Skeleton from "react-loading-skeleton";

function Checkout() {
  const orders = useSelector((state) => state.cart.products);
  const [calculatedPrices, setCalculatedPrices] = useState(_.values(orders));
  const [total, setTotal] = useState(0);

  useEffect(() => {
    const orderPost = _.values(orders).map((order) => ({
      productId: order.productId,
      orderSize: order.orderSize,
    }));
    getOrderTotal(orderPost)
      .then((response) => {
        if (response.data) {
          const newData = response.data.map((cp) => ({
            ...orders[cp.productId],
            calculatedPrice: cp.price,
          }));
          const newTotal = _.values(newData)
            .map((v) => v.calculatedPrice)
            .reduce((prev, curr) => {
              return prev + curr;
            }, 0);
          setTotal(newTotal);
          setCalculatedPrices(newData);
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
    <div className={"flex flex-row xl:mx-auto xl:container h-full p-5"}>
      <div className={"h-full flex-1"}>
        {(() => {
          const arr = [];
          _.values(orders).forEach((product) => {
            console.log(product);
            arr.push(
              <ItemInOrder {...product} itemCount={product.orderSize} />
            );
          });
          return arr;
        })()}
      </div>
      <div
        className={"ml-5 p-5 bg-gray-200 h-full w-1/3 rounded-lg flex flex-col"}
      >
        <div className={"flex-1"}>
          {(() => {
            const arr = [];
            calculatedPrices.forEach((order) => {
              arr.push(
                order.calculatedPrice ? (
                  <div
                    className={
                      "flex flex-row justify-between border-b-2 mb-2 py-2"
                    }
                  >
                    <div className={"w=1/2 text-left text-md"}>
                      {order.name}
                    </div>
                    <div className={"w=1/2 text-right text-md font-bold"}>
                      {order.calculatedPrice} USD
                    </div>
                  </div>
                ) : (
                  <Skeleton height={30} />
                )
              );
            });
            return arr;
          })()}
        </div>
        <div className={"flex flex-row justify-between mb-2 py-2 mt-auto"}>
          <div className={"w=1/2 text-left text-lg font-bold"}>Total</div>
          <div className={"w=1/2 text-right text-lg font-bold"}>
            {total} USD
          </div>
        </div>
        <div className={" w-full flex h-10"}>
          <button
            className={
              "rounded-md mx-auto w-2/3 h-full px-2 font-bold bg-yellow-500"
            }
          >
            Check Out
          </button>
        </div>
      </div>
    </div>
  );
}

export default Checkout;
