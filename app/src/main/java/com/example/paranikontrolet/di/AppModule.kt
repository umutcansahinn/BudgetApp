package com.example.paranikontrolet.di

import com.example.paranikontrolet.data.repository.FirebaseAuthenticatorImpl
import com.example.paranikontrolet.data.repository.FirebaseFirestoreDatabaseImpl
import com.example.paranikontrolet.domain.mapper.BudgetMapper
import com.example.paranikontrolet.domain.repository.FirebaseAuthenticator
import com.example.paranikontrolet.domain.repository.FirebaseFirestoreDatabase
import com.example.paranikontrolet.domain.usecase.UseCases
import com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.*
import com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.forgot_password.ForgotPasswordImpl
import com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.get_current_user.GetCurrentUserImpl
import com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.get_current_user_info.GetCurrentUserInfoImpl
import com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.sign_in.SignInImpl
import com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.sign_out.SignOutImpl
import com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.sign_up.SignUpImpl
import com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase.*
import com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase.delete_budget.DeleteBudgetImpl
import com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase.get_budget_from_firestore.GetBudgetFromFirestoreImpl
import com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase.save_budget.SaveBudgetImpl
import com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase.save_user.SaveUserImpl
import com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase.update_budget.UpdateBudgetImpl
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
    fun provideUseCases(
        auth: FirebaseAuthenticator,
        firestore: FirebaseFirestoreDatabase,
        mapper: BudgetMapper
    ): UseCases {
        return UseCases(
            getCurrentUser = GetCurrentUserImpl(auth = auth),
            signIn = SignInImpl(auth = auth),
            signOut = SignOutImpl(auth = auth),
            signUp = SignUpImpl(auth = auth),
            getCurrentUserInfo = GetCurrentUserInfoImpl(auth = auth),
            forgotPassword = ForgotPasswordImpl(auth = auth),
            getBudgetFromFirestore = GetBudgetFromFirestoreImpl(
                firestore = firestore,
                auth = auth,
                mapper = mapper
            ),
            saveBudget = SaveBudgetImpl(firestore = firestore),
            saveUser = SaveUserImpl(firestore = firestore),
            deleteBudget = DeleteBudgetImpl(firestore = firestore),
            updateBudget = UpdateBudgetImpl(firestore = firestore)
        )
    }

    @Provides
    @Singleton
    fun provideBudgetMapper(): BudgetMapper {
        return BudgetMapper()
    }
}