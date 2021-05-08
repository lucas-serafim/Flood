import { Alert } from 'react-native'

function showError(title: string, message: string) {
    Alert.alert(title, message)
}

function showSuccess(title: string, message: string) {
    Alert.alert(title, message)
}

export { showError, showSuccess }