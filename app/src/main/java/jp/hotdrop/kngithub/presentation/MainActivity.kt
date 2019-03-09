package jp.hotdrop.kngithub.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import jp.hotdrop.kngithub.R
import jp.hotdrop.kngithub.databinding.ActivityMainBinding
import jp.hotdrop.kngithub.databinding.RowContributorBinding
import jp.hotdrop.kngithub.presentation.component.RecyclerViewAdapter
import jp.hotdrop.model.Contributor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.viewModel
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        observe()
    }

    private fun observe() {
        viewModel.contributors.observe(this, Observer {
            it?.let {
                binding.progressBar.visibility = View.GONE
                onLoadContributors(it)
            }
        })
        viewModel.error.observe(this, Observer {
            it?.let {
                binding.progressBar.visibility = View.GONE
                Snackbar.make(binding.contentView, "エラーが発生しました。", Snackbar.LENGTH_INDEFINITE).show()
            }
        })
        lifecycle.addObserver(viewModel)
    }

    private fun onLoadContributors(contributors: List<Contributor>) {
        if (contributors.isEmpty()) {
            Snackbar.make(binding.contentView, "0件です・・", Snackbar.LENGTH_INDEFINITE).show()
            return
        }

        binding.recyclerView.adapter = ContributorAdapter().apply { addAll(contributors) }
        binding.recyclerView.visibility = View.VISIBLE
    }

    inner class ContributorAdapter: RecyclerViewAdapter<Contributor, RecyclerViewAdapter.BindingHolder<RowContributorBinding>>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder<RowContributorBinding> {
            return BindingHolder(parent, R.layout.row_contributor)
        }

        override fun onBindViewHolder(holder: BindingHolder<RowContributorBinding>, position: Int) {
            val binding = holder.binding
            binding?.let { rowBinding ->
                val contributor = getItem(position)
                rowBinding.contributor = contributor
                Glide.with(this@MainActivity)
                    .load(contributor.avatarUrl)
                    .apply(RequestOptions().circleCrop())
                    .into(binding.avatarImage)
            }
        }
    }
}
