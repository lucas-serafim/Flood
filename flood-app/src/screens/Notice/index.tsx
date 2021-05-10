import React from 'react'
import { StyleSheet, ScrollView } from 'react-native'
import NoticeCard from '../NoticeCard'

function Notice() {
    return (
        <ScrollView style = {styles.container}>
            <NoticeCard />
        </ScrollView>
    )
}

const styles = StyleSheet.create({
    container: {
        paddingRight: '5%',
        paddingLeft: '5%',
    }
})

export default Notice