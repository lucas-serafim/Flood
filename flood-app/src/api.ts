import axios from 'axios';
import { Platform } from 'react-native';

const API_URL = Platform.OS === 'ios' ? 'http://localhost:8080' : 'http://192.168.1.112:8080'

export function signIn(body: object) {
    return axios.post(`${API_URL}/users/login`, body)
}

export async function signUp(body: object) {
    return axios.post(`${API_URL}/users`, body)
}

/*export function fetchOrders() {
   return axios(`${API_URL}/orders`)
}

export function confirmDelivery(orderId: number){
   return axios.put(`${API_URL}/orders/${orderId}/delivered`)
}*/