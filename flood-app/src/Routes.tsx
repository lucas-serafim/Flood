import React from 'react'
import { NavigationContainer } from '@react-navigation/native'
import { createStackNavigator } from '@react-navigation/stack'
import { createDrawerNavigator } from '@react-navigation/drawer'

import LoginAndRegister from './screens/LoginAndRegister'
import Notice from './screens/Notice'
import Map from './screens/Map'

const Stack = createStackNavigator()
const Drawer = createDrawerNavigator();

const DrawerNavigator = () => {
   return (
      <Drawer.Navigator>
         <Drawer.Screen name="Notice" options={{ title: 'Noticias' }}>
            {() => <Notice />}
         </Drawer.Screen>

         <Drawer.Screen name="Map" options={{ title: 'Mapa' }}>
            {() => <Map />}
         </Drawer.Screen>
      </Drawer.Navigator>
   )
}

const AuthNavigator = () => {
   return (
      <Stack.Navigator headerMode="none" screenOptions={{ headerShown: false }}>
         <Stack.Screen name="Home" component={LoginAndRegister}></Stack.Screen>
         <Stack.Screen name="Notice" component={DrawerNavigator}></Stack.Screen>
         <Stack.Screen name="Map" component={Map}></Stack.Screen>
      </Stack.Navigator>
   )
}

const Routes = () => {
   return (
      <NavigationContainer>
         <AuthNavigator />
      </NavigationContainer>
   )
}

export default Routes