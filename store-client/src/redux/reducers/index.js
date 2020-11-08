import { combineReducers } from 'redux';
import CartReducer from "./CartReducer";

const rootReducer = combineReducers({
    cart: CartReducer
});

export default rootReducer;