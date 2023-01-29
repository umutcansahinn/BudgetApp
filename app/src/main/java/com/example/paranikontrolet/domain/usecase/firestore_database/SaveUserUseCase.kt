package com.example.paranikontrolet.domain.usecase.firestore_database

import com.example.paranikontrolet.domain.repository.FirebaseFirestoreDatabase
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val firestore: FirebaseFirestoreDatabase
) {

    suspend operator fun invoke(
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