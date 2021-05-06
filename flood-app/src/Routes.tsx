import React from 'react'
import { NavigationContainer } from '@react-navigation/native'
import { createStackNavigator } from '@react-navigation/stack'

import LoginAndRegister from './screens/LoginAndRegister'

const Stack = createStackNavigator()

function Routes() {
    return (
        <NavigationContainer>
           <Stack.Navigator headerMode = "none" screenOptions = {{ cardStyle: { backgroundColor: '#FFF' }}}>
              <Stack.Screen name = "Home" component = { LoginAndRegister }></Stack.Screen>
           </Stack.Navigator>
        </NavigationContainer>
     )
}

export default Routes