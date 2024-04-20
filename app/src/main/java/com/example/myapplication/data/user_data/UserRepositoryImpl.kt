package com.example.myapplication.data.user_data

class UserRepositoryImpl (
    private val userDao: UserDao
): UserRepository{
    override suspend fun insertUser(user: User) {
        return userDao.insertUser(user)
    }

    override suspend fun getUser(): User? {
        return userDao.getUser()
    }
}