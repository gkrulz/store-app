import { Link } from "react-router-dom";
import Skeleton from "react-loading-skeleton";
import { toast } from "react-toastify";

function Item({
  productId,
  name,
  price,
  cartonSize,
  imageUrl,
  description,
  itemCount,
}) {
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
      <div className={"h-36 w-40 flex flex-col text-gray-800"}>
        <div className={"my-auto mx-auto text-sm"}>
          x <span className={"text-bold"}>{itemCount}</span> items in cart
        </div>
      </div>
    </div>
  );
}

export default Item;
