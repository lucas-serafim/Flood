import React from 'react'
import { Image, StyleSheet, Text, View } from 'react-native'

function NoticeCard() {
    return (
        <View style = {styles.container}>
            <View style = {styles.header}>
                <Image style = {styles.image} source = {require('../../assets/logo.png')} />
            </View>
        </View>
    )
}

const styles = StyleSheet.create({
    container: {
        marginTop: '10%',
        marginLeft: '2%',
        marginRight: '2%',
        marginBottom: '2%',
        padding: 15,
        backgroundColor: '#FFF',
        shadowOpacity: 0.25,
        shadowColor: '#000',
        shadowOffset: { width: 0, height: 4 },
        shadowRadius: 20,
        borderRadius: 10,
        elevation: 5
    },
    header: {
        flexDirection: 'row',
        justifyContent: 'space-between'
    },
    image: {
        width: '100%'
    }
})

export default NoticeCard