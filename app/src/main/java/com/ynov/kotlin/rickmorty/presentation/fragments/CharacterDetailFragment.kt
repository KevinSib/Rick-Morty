package com.ynov.kotlin.rickmorty.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.adapters.BaseRecyclerViewAdapter
import com.ynov.kotlin.rickmorty.presentation.adapters.EpisodesRecyclerViewAdapters
import com.ynov.kotlin.rickmorty.presentation.viewHolders.BaseViewHolder
import com.ynov.kotlin.rickmorty.presentation.viewModels.CharacterDetailViewModel
import jp.wasabeef.picasso.transformations.BlurTransformation


class CharacterDetailFragment(var characterId: Long) : Fragment(),
    BaseRecyclerViewAdapter.IRecyclerViewManager,
    BaseViewHolder.IItemOnClickListener {

    //region Variables

    override val items: MutableList<Any>
        get() {
            viewModel?.let {
                it.mItem.value?.let {
                    return mutableListOf(it.episode)
                }
            }
            return mutableListOf()
        }

    override val onClickListenerManager: BaseViewHolder.IItemOnClickListener
        get() = this

    private var mName: TextView? = null
    private var mStatus: TextView? = null
    private var mSpecie: TextView? = null
    private var mType: TextView? = null
    private var mGender: TextView? = null
    private var mOrigin: TextView? = null
    private var mLocation: TextView? = null
    private var mLoadingProgress: ProgressBar? = null
    private var mRecyclerView: RecyclerView? = null
    private var mCoverImageView: ImageView? = null
    private var mProfilImageView: ImageView? = null

    private val viewModel: CharacterDetailViewModel by lazy {
        ViewModelProviders.of(this).get(CharacterDetailViewModel::class.java)
    }

    //endregion

    //region Default Methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
        initViewModelObserver()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val r = inflater.inflate(R.layout.fragment_character_detail, container, false)
        initView(r)
        return r
    }

    override fun onStart() {

        super.onStart()

        initRecyclerView()
        viewModel.start(id = characterId)

    }

    companion object {
        val CHARACTER_ID: String = "CHAR_ID"
        fun newInstance(id: Long) = CharacterDetailFragment(id)
    }

    //endregion

    //region Methods

    private fun initView(view: View) {
        mLoadingProgress = view.findViewById(R.id.fragment_characters_detail_loading_progressbar)
        mRecyclerView = view.findViewById(R.id.fragment_charactersd_detail_recyclerview)
        mName = view.findViewById(R.id.fragment_character_detail_name_textview)
        mStatus = view.findViewById(R.id.fragment_character_detail_status_textview)
        mSpecie = view.findViewById(R.id.fragment_character_detail_specie_textview)
        mType = view.findViewById(R.id.fragment_character_detail_type_textview)
        mOrigin = view.findViewById(R.id.fragment_character_detail_origin_textview)
        mLocation = view.findViewById(R.id.fragment_character_detail_location_textview)
        mGender = view.findViewById(R.id.fragment_character_detail_gender_textview)
        mCoverImageView = view.findViewById(R.id.fragment_character_detail_cover_imageview)
        mProfilImageView = view.findViewById(R.id.fragment_character_detail_profil_imageview)
    }

    private fun initRecyclerView() {
        mRecyclerView?.let {

            val adapter = EpisodesRecyclerViewAdapters()
            adapter.manager = this

            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = adapter

        }
    }

    private fun initViewModelObserver() {
        viewModel.mIsLoading.observe(this, Observer {
            mLoadingProgress?.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewModel.mItem.observe(this, Observer {
            mCoverImageView?.let { imgView ->
                Picasso
                    .get()
                    .load(it.image)
                    .resize(imgView.width, imgView.height)
                    .centerCrop()
                    .transform(BlurTransformation(requireContext(), 25, 1))
                    .into(imgView)
            }
            mProfilImageView?.let { imgView ->
                Picasso
                    .get()
                    .load(it.image)
                    .into(imgView)
            }
            mName?.text = it.name
            mStatus?.text = it.status
            mSpecie?.text = it.species
            mGender?.text = it.gender
            mType?.text = it.type
            mOrigin?.text = it.origin.name
            mLocation?.text = it.location.name
            mRecyclerView?.adapter?.notifyDataSetChanged()
        })
    }

    //endregion

    //region IRecyclerView Delegate

    override fun numberOfItem(): Int = items.size

    override fun getItemAtPosition(position: Int): Any = items.get(position)

    override fun OnClickRecyclerViewItem(obj: Any, atPosition: Int) {
        //  TODO manage click if needed
    }

    //endregion

}
