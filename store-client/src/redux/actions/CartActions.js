import { CartConstants } from "../constants";

export default {
  addItem,
};
function addItem(item) {
  return (dispatch, getState) => {
    dispatch({
      type: CartConstants.ADD_ITEM,
      payload: item,
    });
  };
}
