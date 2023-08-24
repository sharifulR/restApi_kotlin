package com.wbsoft.tstpdmo.models


import com.google.gson.annotations.SerializedName

data class AllClassM(
    @SerializedName("classData")
    val classData: List<ClassData>,
    @SerializedName("message")
    val message: String,
    @SerializedName("subjectData")
    val subjectData: List<SubjectData>
) {
    data class ClassData(
        @SerializedName("chapterCountNumber")
        val chapterCountNumber: Int,
        @SerializedName("classData")
        val classData: ClassData,
        @SerializedName("postCountNumber")
        val postCountNumber: Int,
        @SerializedName("subjectCountNumber")
        val subjectCountNumber: Int
    ) {
        data class ClassData(
            @SerializedName("class_name")
            val className: String,
            @SerializedName("created_at")
            val createdAt: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("status")
            val status: Int,
            @SerializedName("updated_at")
            val updatedAt: String
        )
    }

    data class SubjectData(
        @SerializedName("classname_id")
        val classnameId: Int,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("department_id")
        val departmentId: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("status")
        val status: Int,
        @SerializedName("subject_image")
        val subjectImage: String,
        @SerializedName("subject_name")
        val subjectName: String,
        @SerializedName("updated_at")
        val updatedAt: String
    )
}