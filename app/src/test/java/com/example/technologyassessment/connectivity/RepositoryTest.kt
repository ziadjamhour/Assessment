package com.example.technologyassessment.connectivity

import com.example.technologyassessment.utils.Constants
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class RepositoryTest{
    @Test
    fun testParamsValidity(){
        val key : String = Constants.KEY
        val section : String = Constants.SECTION
        val periode : String = Constants.PERIODE
        val result = Repository.checkParamsValidity(section, periode, key)
        assert(result)
    }
}