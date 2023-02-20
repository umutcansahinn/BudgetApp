package com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase.save_user

import com.example.paranikontrolet.domain.repository.FirebaseFirestoreDatabase
import javax.inject.Inject

class SaveUserImpl @Inject constructor(
    private val firestore: FirebaseFirestoreDatabase
) : SaveUser {

    override suspend operator fun invoke(
        getFirebaseUserUid: String,
        email: String,
        name: String,
        password: String
    ) {
        firestore.saveUser(
            getFirebaseUserUid = getFirebaseUserUid,
            email = email,
            name = name,
            password = password
        )

    }
}