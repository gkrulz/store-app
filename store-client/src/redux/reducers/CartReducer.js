import { CartConstants } from "../constants";

export default function (
  state = {
    products: {},
  },
  action
) {
  switch (action.type) {
    case CartConstants.ADD_ITEM: {
      const existing = state.products[action.payload.productId];
      return {
        ...state,
        products: {
          ...state.products,
          [action.payload.productId]: {
            ...(existing ? existing : {}),
            ...action.payload,
            orderSize: existing
              ? existing.orderSize + action.payload.orderSize
              : action.payload.orderSize,
          },
        },
      };
    }
    default: {
      return state;
    }
  }
}
