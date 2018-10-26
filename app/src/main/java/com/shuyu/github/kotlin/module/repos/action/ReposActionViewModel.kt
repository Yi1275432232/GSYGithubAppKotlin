package com.shuyu.github.kotlin.module.repos.action

import android.app.Application
import com.shuyu.github.kotlin.common.net.ResultCallBack
import com.shuyu.github.kotlin.model.ui.ReposUIModel
import com.shuyu.github.kotlin.module.base.BaseViewModel
import com.shuyu.github.kotlin.repository.ReposRepository
import javax.inject.Inject

/**
 * Created by guoshuyu
 * Date: 2018-10-26
 */

class ReposActionViewModel @Inject constructor(private val reposRepository: ReposRepository, application: Application) : BaseViewModel(application) {

    val reposUIModel = ReposUIModel()

    var userName: String = ""

    var reposName: String = ""


    override fun loadDataByRefresh() {
        reposRepository.getRepoInfo(userName, reposName, object : ResultCallBack<ReposUIModel> {
            override fun onSuccess(result: ReposUIModel?) {
                result?.apply {
                    reposUIModel.cloneFrom(this)
                }
            }

            override fun onFailure() {
            }
        })
        loadData()
    }

    override fun loadDataByLoadMore() {
        loadData()
    }

    private fun loadData() {
        clearWhenRefresh()
        reposRepository.getReposEvents(userName, reposName, this, page)
    }
}