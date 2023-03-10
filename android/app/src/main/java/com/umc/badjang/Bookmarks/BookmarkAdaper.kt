package com.umc.badjang.Bookmarks

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.badjang.R
import com.umc.badjang.databinding.MainMoreNationalNewsItemBinding
import com.umc.badjang.databinding.PostContentItemBinding

class BookmarkAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var scholarshipViewBinding: MainMoreNationalNewsItemBinding
    private lateinit var postViewBinding: PostContentItemBinding

    var items = mutableListOf<BookmarkItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        TYPE_SCHOLARSHIP -> {
            val view = LayoutInflater.from(context).inflate(R.layout.main_more_national_news_item, parent, false)

            scholarshipViewBinding = MainMoreNationalNewsItemBinding.bind(view)

            ScholarshipHolder(MainMoreNationalNewsItemBinding.bind(view))
        }
        TYPE_POST -> {
            val view = LayoutInflater.from(context).inflate(R.layout.post_content_item, parent, false)

            postViewBinding = PostContentItemBinding.bind(view)

            PostHolder(PostContentItemBinding.bind(view))
        }
        else -> {
            throw IllegalStateException("Not Found ViewHolder Type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ScholarshipHolder -> {
                holder.bind(items[position] as BookmarkScholarshipData)
            }
            is PostHolder -> {
                holder.bind(items[position] as BookmarkPostData)
            }
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = when (items[position]) {
        is BookmarkScholarshipData -> {
            TYPE_SCHOLARSHIP
        }
        is BookmarkPostData -> {
            TYPE_POST
        }
        else -> {
            throw IllegalStateException("Not Found ViewHolder Type")
        }
    }

    class ScholarshipHolder(private val binding: MainMoreNationalNewsItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BookmarkScholarshipData) {
            binding.nationalNewsInstitutionLabel.text = item.bookmarkScholarshipInstitution // ?????????

            // ???????????? ?????? - ?????? ??????
            binding.nationalNewsCloseTitle.text = item.bookmarkScholarshipTitle             // ???????????? ??????
            binding.nationalNewsCloseImg.setImageBitmap(item.bookmarkScholarshipImg)        // ???????????? ?????????

            // ???????????? ?????? - ?????? ??????
            binding.nationalNewsOpenTitle.text = item.bookmarkScholarshipTitle             // ???????????? ??????
            binding.nationalNewsOpenText.text = item.bookmarkScholarshipContent            // ??????????????????
            binding.nationalNewsOpenImg.setImageBitmap(item.bookmarkScholarshipImg)        // ???????????? ?????????

            // ?????? ??????
            binding.nationalNewsCommentsNum.text = item.bookmarkScholarshipCommentsCnt.toString() // ?????? ???
            binding.nationalNewsViewNum.text = item.bookmarkScholarshipViewCnt.toString()         // ?????????

            // ???????????? ?????? ?????? ?????? ???
            binding.nationalNewsBookmarkCheckBtn.setOnClickListener {
                binding.nationalNewsBookmarkCheckBtn.visibility = View.GONE
                binding.nationalNewsBookmarkUncheckBtn.visibility = View.VISIBLE
            }

            // ???????????? ?????? ?????? ?????? ???
            binding.nationalNewsBookmarkUncheckBtn.setOnClickListener {
                binding.nationalNewsBookmarkCheckBtn.visibility = View.VISIBLE
                binding.nationalNewsBookmarkUncheckBtn.visibility = View.GONE
            }

            // ????????? ?????? ?????? ???
            binding.nationalNewsOpenBtn.setOnClickListener(View.OnClickListener {
                // ?????? ?????? ????????? ?????? ?????? ????????????
                binding.nationalNewsCloseContents.visibility = View.GONE
                binding.nationalNewsOpenContents.visibility = View.VISIBLE

                // ????????? ?????? ????????? ?????? ?????? ????????????
                binding.nationalNewsCloseBtn.visibility = View.VISIBLE
                binding.nationalNewsOpenBtn.visibility = View.GONE
            })

            // ?????? ?????? ?????? ???
            binding.nationalNewsCloseBtn.setOnClickListener(View.OnClickListener {
                // ?????? ?????? ????????? ?????? ?????? ????????????
                binding.nationalNewsCloseContents.visibility = View.VISIBLE
                binding.nationalNewsOpenContents.visibility = View.GONE

                // ?????? ?????? ????????? ????????? ?????? ????????????
                binding.nationalNewsCloseBtn.visibility = View.GONE
                binding.nationalNewsOpenBtn.visibility = View.VISIBLE
            })
        }
    }

    class PostHolder(private val binding: PostContentItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BookmarkPostData) {
            // ????????? ?????????
            binding.popularPostProfileImg.setImageBitmap(item.bookmarkPostProfileImg) // ????????? ?????????
            binding.popularPostProfileNickname.text = item.bookmarkPostNickname       // ?????????
            binding.popularPostProfileDate.text = item.bookmarkPostDate               // ?????????

            // ????????? ??????
            binding.popularPostContentTitle.text = item.bookmarkPostTitle             // ????????? ??????
            binding.popularPostContentText.text = item.bookmarkPostContent            // ????????? ??????
            binding.popularPostContentImg.setImageBitmap(item.bookmarkPostImg)        // ????????? ?????????

            // ?????? ??????
            binding.popularPostCommentsNum.text = item.bookmarkPostCommentsCnt.toString() // ?????? ???
            binding.popularPostViewNum.text = item.bookmarkPostViewCnt.toString()         // ?????????
            binding.popularPostGoodNum.text = item.bookmarkPostGoodCnt.toString()         // ????????? ???
        }
    }


    fun addItems(item: BookmarkItem) {
        this.items.add(item)
        this.notifyDataSetChanged()
    }

    companion object {
        private const val TYPE_SCHOLARSHIP = 0
        private const val TYPE_POST = 1
    }
}