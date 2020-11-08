import axios from "axios";

const BASE_URL = "http://localhost:8090";

export async function getProducts() {
  return await axios.get(`${BASE_URL}/products`);
}

export async function getOrderTotal(data) {
  return await axios.post(`${BASE_URL}/order`, data);
}
