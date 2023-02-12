package com.example.paranikontrolet.di

import com.example.paranikontrolet.data.repository.FirebaseAuthenticatorImpl
import com.example.paranikontrolet.data.repository.FirebaseFirestoreDatabaseImpl
import com.example.paranikontrolet.domain.mapper.BudgetMapper
import com.example.paranikontrolet.domain.repository.FirebaseAuthenticator
import com.example.paranikontrolet.domain.repository.FirebaseFirestoreDatabase
import com.example.paranikontrolet.domain.usecase.AuthUseCase
import com.example.paranikontrolet.domain.usecase.FirestoreUseCase
import com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.*
import com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseUser() = Firebase.auth
    @Provides
    @Singleton
    fun provideFirebaseFirestore() = Firebase.firestore

    @Provides
    @Singleton
    fun provideFirebaseAuthenticator(auth: FirebaseAuth): FirebaseAuthenticator {
        return FirebaseAuthenticatorImpl(auth)
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestoreDatabase(firestore: FirebaseFirestore): FirebaseFirestoreDatabase {
        return FirebaseFirestoreDatabaseImpl(firestore)
    }

    @Provides
    @Singleton
    fun provideFirebaseAuthUseCases(auth: FirebaseAuthenticator): AuthUseCase {
        return AuthUseCase(
            getCurrentUser = GetCurrentUser(auth = auth),
            signIn = SignIn(auth = auth),
            signOut = SignOut(auth = auth),
            signUp = SignUp(auth = auth),
            getCurrentUserInfo = GetCurrentUserInfo(auth = auth),
            forgotPassword = ForgotPassword(auth = auth)
        )
    }
    @Provides
    @Singleton
    fun provideBudgetMapper(): BudgetMapper {
        return BudgetMapper()
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestoreUseCases(
        firestore: FirebaseFirestoreDatabase,
        mapper: BudgetMapper
    ): FirestoreUseCase {

        return FirestoreUseCase(
            getBudgetFromFirestore = GetBudgetFromFirestore(
                firestore = firestore,
                mapper = mapper
            ) ,
            saveBudget = SaveBudget(firestore = firestore),
            saveUser = SaveUser(firestore = firestore),
            deleteBudget = DeleteBudget(firestore = firestore),
            updateBudget = UpdateBudget(firestore = firestore)
        )
    }


}