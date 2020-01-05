package com.example.poverty.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities= arrayOf(RecycleViewPost::class), version = 1, exportSchema = false)
abstract class PostRoomDatabase:RoomDatabase() {

    abstract fun postDatabaseDao() : PostDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: PostRoomDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope): PostRoomDatabase {

            return INSTANCE ?:synchronized(this) {

                var instance = Room.databaseBuilder(
                    context.applicationContext,
                    PostRoomDatabase::class.java,
                    "post_database"
                )
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }


        }
    }

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.postDatabaseDao())
                }
            }
        }

        suspend fun populateDatabase(postDao: PostDatabaseDao) {
            // Delete all content here.
            postDao.deleteAll()

            // Add sample words.
            var post1 = RecycleViewPost(1, "The Poor Children In Africa", "The poverty in Africa has been increasing recently and they needs your help","13/Mar/2019","Nearly half of all children in sub-Saharan Africa are living in extreme poverty, according to a joint Unicef-World Bank report released on Tuesday, with figures showing that almost 385 million children worldwide survive on less than \$1.90 (£1.50) a day, the World Bank international poverty line.","https://raw.githubusercontent.com/yujune/AssignmentPostImage/master/1503608938-598.jpg")
            postDao.insert(post1)

            var post2 = RecycleViewPost(2, "The Definition of Poor: Black Children","Poor doesnt means bad, they just dont hav the right education", "13/Jun/2019","Poverty is a state or condition in which a person or community lacks the financial resources and essentials for a minimum standard of living. Poverty means that the income level from employment is so low that basic human needs can't be met.","https://raw.githubusercontent.com/yujune/AssignmentPostImage/master/Poverty.jpg")
            postDao.insert(post2)

            var post3 = RecycleViewPost(3, "What is Poverty?", "Poverty is defined as the lack of education and lack of knowledge.","5/Dec/2019","Poverty is not having enough material possessions or income for a person's needs. Poverty may include social, economic, and political elements. Absolute poverty is the complete lack of the means necessary to meet basic personal needs, such as food, clothing and shelter.","https://raw.githubusercontent.com/yujune/AssignmentPostImage/master/poverty-a2.jpg")
            postDao.insert(post3)

            var post4 = RecycleViewPost(4, "A Many A Little Make Mickles", "Earn a little, Save everyday, use it wisely, you can become the millionaire.","13/Nov/2019","You can help the poor and needy people in multiple ways and for this you don’t need to go and find poor and needy people as you can go to slum areas and distribute the gifts and give financial aid to the poor and needy people. You can also go to the school and provide free scholarships to bright and deserving students. You can also go the hospitals and pay the bills of poor and deserving patients. You can also provide financial help to the underprivileged people.","https://raw.githubusercontent.com/yujune/AssignmentPostImage/master/helpthepoor.jpg")
            postDao.insert(post4)

            var post5 = RecycleViewPost(5, "Donate The Poor", "You have the money? Why dont you save the extra money and help for the poor one","14/Jan/2018","A donation is a gift for charity, humanitarian aid, or to benefit a cause. A donation may take various forms, including money, alms, services, or goods such as clothing, toys, food, or vehicles. ... Charitable donations of goods or services are also called gifts in kind.","https://raw.githubusercontent.com/yujune/AssignmentPostImage/master/donation.png")
            postDao.insert(post5)


        }
    }
}