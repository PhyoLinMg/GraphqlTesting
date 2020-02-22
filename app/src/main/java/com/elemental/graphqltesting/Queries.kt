package com.elemental.graphqltesting

import com.elemental.graphqltesting.data.model.GraphqlQuery

class Queries {
    companion object{
        fun insertToDo(title:String,isPublic:Boolean):GraphqlQuery{
            return GraphqlQuery("mutation{\n" +
                    "  insert_todos(objects:{title:\"$title\",is_public:$isPublic}){\n" +
                    "    affected_rows\n" +
                    "    returning{\n" +
                    "      user{\n" +
                    "        name\n" +
                    "        todos{\n" +
                    "          id\n" +
                    "          is_public\n" +
                    "          title\n" +
                    "        }\n" +
                    "      }\n" +
                    "    }\n" +
                    "  }\n" +
                    "}")
        }

        fun getUserName(id:String):GraphqlQuery{
            return GraphqlQuery("{\n" +
                    "  users(where: {id:{_eq:\"$id\"}}){\n" +
                    "    name\n" +
                    "  }\n" +
                    "}")
        }

    }

}