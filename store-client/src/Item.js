import { Link } from "react-router-dom";
import Skeleton from "react-loading-skeleton";
import { toast } from "react-toastify";
import { useDispatch } from "react-redux";
import { CartActions } from "./redux/actions";
import InputNumeric from "react-input-numeric";
import { useState } from "react";

function Item({
  productId,
  name,
  price,
  cartonSize,
  imageUrl,
  description,
  onAddToCard,
}) {
  const dispatch = useDispatch();
  const MAX_ORDER_COUNT = 100;
  const [orderCount, setOrderCount] = useState(1);

  return (
    <div className={"w-full flex flex-row h-36 p-1"}>
      <div className={"h-32 w-32 overflow-hidden rounded-lg"}>
        {imageUrl ? (
          <img
            src={imageUrl}
            className={"h-full w-full object-contain border-2 p-1 rounded-lg"}
          />
        ) : (
          <Skeleton height={400} />
        )}
      </div>
      <div className={"flex-1 mx-3 flex flex-col"}>
        <h1 className={"w-2/3 font-bold text-lg mt-1"}>
          {name ? name : <Skeleton />}
        </h1>
        <div className={"text-sm mt-1"}>
          <div className={"text-gray-700"}>
            {cartonSize ? (
              <div>
                <span className={"font-bold"}>{cartonSize}</span> items in one
                carton
              </div>
            ) : (
              <Skeleton />
            )}
            {description ? (
              <span className={"text-gray-700"}>{description}</span>
            ) : (
              <Skeleton />
            )}
          </div>
        </div>
      </div>
      <div className={"h-full w-40 flex flex-col justify-end"}>
        {price ? (
          <div className={"font-bold mx-auto mt-auto"}>{price} USD</div>
        ) : (
          <Skeleton />
        )}
        {price ? (
          <div className={"mx-auto text-xs text-gray-500"}>per carton</div>
        ) : (
          <Skeleton />
        )}
        <div className={"w-full flex flex-col"}>
          {productId ? (
            <div className={"mx-auto my-2"}>
              <InputNumeric
                value={orderCount}
                min={1}
                max={MAX_ORDER_COUNT}
                onChange={(value) => setOrderCount(value)}
              />{" "}
            </div>
          ) : (
            ""
          )}
          {productId ? (
            <span className={"text-sm text-gray-500 mx-auto"}>items</span>
          ) : (
            ""
          )}
        </div>
        {productId ? (
          <button
            className={"rounded-md px-2 font-bold bg-yellow-500"}
            onClick={() => {
              dispatch(
                CartActions.addItem({
                  productId: productId,
                  orderSize: orderCount,
                  name: name,
                  price: price,
                  cartonSize: cartonSize,
                  imageUrl: imageUrl,
                  description: description,
                })
              );
              toast.success("Added to cart!", {
                position: "top-center",
                autoClose: 5000,
                hideProgressBar: true,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
              });
            }}
          >
            Add to Card
          </button>
        ) : (
          <Skeleton />
        )}
      </div>
    </div>
  );
}

export default Item;
