import axios from "axios";

const API_URL = 'http://192.168.1.108:8080'

export async function signIn(body: object) {
    await axios.post(`${API_URL}/users/login`, body)
    .then(function (response) {
        console.log(response.status)
    })
    .catch(function (error) {
        console.log(error)
    })
}

export function signUp(body: object) {
    axios.post(`${API_URL}/users`, body)
}

/*export function fetchOrders() {
   return axios(`${API_URL}/orders`)
}

export function confirmDelivery(orderId: number){
   return axios.put(`${API_URL}/orders/${orderId}/delivered`)
}*/