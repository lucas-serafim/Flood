import React, { useState } from 'react'
import { StyleSheet, Image, Text, View, TextInput, TouchableOpacity, Alert } from 'react-native'
import { showSuccess, showError } from '../../commom'
import { signIn, signUp } from '../../api'

function LoginAndRegister() {
    const [isRegister, setIsRegister] = useState(false)

    const [name, setName] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [validPassword, setValidPassword] = useState('')

    const OnPressRegisterInputs = () => {
        setIsRegister(isRegister ? false : true)
    }

    const OnPressSignIn = () => {

        const body = {
            email,
            password
        }

        if (!email || !password) {
            showError("Status do Login", "Preencha todos os campos!")
        } else {
            signIn(body)
                .then(response => {
                    showSuccess("Status do Login", "Login realizado com sucesso!")
                })
                .catch(error => {
                    const statusCode = error.response.status
                    let message = ''

                    if (statusCode === 404) {
                        message = "Usuário ou senha invalidos"
                    } else if (statusCode === 500) {
                        message = "Ocorreu um erro inesperado.\nPor favor, tente novamente mais tarde"
                    }
                    
                    showError("Status do Login", message)
                })
        }
    }

    const OnPressSignUp = () => {

        const body = {
            name,
            email,
            password,
            urlImage: null,
            createdAt: new Date()
        }

        if (!name || !email || !password || !validPassword) {
            showError("Status do Cadastro", "Preencha todos os campos!")
        } else if (password === validPassword) {
            signUp(body)
                .then(() => showSuccess("Status do Cadastro", "Cadastro realizado com sucesso!"))
                .catch(() => showError("Status do Cadastro", "Ocorreu um erro inesperado.\nPor favor, tente novamente mais tarde"))
                .finally(() => setIsRegister(isRegister ? false : true))
        } else {
            showError("Status do Cadastro", "As senhas são diferentes!")
        }
    }

    return( 
        <>
            <View style = { [styles.container] }>
                <Image style={styles.logo} source={require('../../assets/logo2.png')} />
                <Text style = { styles.title }>Flood</Text>                

                <View style = { styles.inputs }>
                    {
                        isRegister 
                        ?
                        (
                            <>
                                <Text style = { styles.textInput }>Nome e Sobrenome</Text>
                                <TextInput style = { styles.input } onChangeText = {text => setName(text)} />
                            </>
                        )
                        :
                        (
                            <>
                            </>
                        )
                    }

                    <Text style = { styles.textInput }>E-mail</Text>
                    <TextInput style = { styles.input } onChangeText = {text => setEmail(text)} />

                    <Text style = { styles.textInput }>Senha</Text>
                    <TextInput style = { styles.input } secureTextEntry = { true } onChangeText = {text => setPassword(text)} />

                    {
                        isRegister 
                        ?
                        (
                            <>
                                <Text style = { styles.textInput }>Confirmar Senha</Text>
                                <TextInput style = { styles.input } secureTextEntry = { true } onChangeText = {text => setValidPassword(text)} />
                            </>
                        )
                        :
                        (
                            <>
                            </>
                        )
                    }
                </View>

                <TouchableOpacity style = { styles.btnSignIn } onPress = { isRegister ? OnPressSignUp : OnPressSignIn }>
                    <Text style = { styles.btnTextSignIn }>{ isRegister ? "Cadastrar" : "Entrar" }</Text>
                </TouchableOpacity>

                <TouchableOpacity style = { styles.btnSignUp } onPress = { OnPressRegisterInputs }>
                    <Text style = { styles.btnTextSignUp }>{ isRegister ? "Voltar" : "Não possui cadastro?"}</Text>
                </TouchableOpacity>

                {/*
                    !isRegister
                    ?
                    (
                        <>
                            <TouchableOpacity style = { styles.btnForgotPassword }>
                                <Text>Esqueci minha senha</Text>
                            </TouchableOpacity>
                        </>
                    )
                    :
                    (
                        <>
                        </>
                    )
                */}
            </View>
        </>
    )
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: "center",
        alignItems: "center",
        width: '100%',
        padding: 42,
    },
    view: {
        flex: 1,
        width: '100%',
        justifyContent: "center",
        alignItems: 'center'
    },
    logo: {
        width: '50%',
        height: '25%',
    },
    title: {
        fontStyle: 'italic',
        fontSize: 36,
        fontWeight: "bold",
        color: '#225051',
        marginBottom: '10%'
    },
    inputs: {
        width: '100%'
    },
    textInput: {
        fontSize: 17,
        color: '#969696',
    },
    input: {
        fontSize: 16,
        borderBottomWidth: 2,
        borderBottomColor: '#8a8a8a',
        marginBottom: 17
    },
    btnSignIn: {
        width: '90%',
        backgroundColor: '#225051',
        padding: 15,
        borderRadius: 50,
        alignItems: "center",
        justifyContent: "center",
        marginTop: '10%'
    },
    btnSignUp: {
        borderWidth: 2,
        borderColor: '#225051',
        borderRadius: 50,
        padding: 15,
        width: '90%',
        alignItems: "center",
        marginTop: 15,
    },
    btnForgotPassword: {
        color: '#225051',
        fontSize: 15,
        marginTop: 16
    },
    btnTextSignIn: {
        color: '#fff',
        fontWeight: "bold",
        fontSize: 18
    },
    btnTextSignUp: {
        color: '#225051',
        fontSize: 15
    }
})

export default LoginAndRegister