package com.ynov.kotlin.rickmorty.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.entity.Character
import com.ynov.kotlin.rickmorty.data.entity.Episode
import com.ynov.kotlin.rickmorty.presentation.adapters.BaseRecyclerViewAdapter
import com.ynov.kotlin.rickmorty.presentation.adapters.EpisodesRecyclerViewAdapters
import com.ynov.kotlin.rickmorty.presentation.viewHolders.BaseViewHolder
import com.ynov.kotlin.rickmorty.presentation.viewHolders.ClickHandler
import com.ynov.kotlin.rickmorty.presentation.viewModels.CharacterDetailViewModel
import jp.wasabeef.picasso.transformations.BlurTransformation
import kotlinx.android.synthetic.main.fragment_character_detail.*


//  FIXME We pass here an information by the manufacturer because via the bundle
//   it creates an error that causes the parent activity to crash. No answer has
//   yet been found. CAUTION ! this means that the rotation of the screen will not work properly.
//   For the moment the rotation of this screen has been blocked
class CharacterDetailFragment(var characterId: Long) : BaseFragment<CharacterDetailViewModel>() {

    //region Variables

    override var layoutId: Int = R.layout.fragment_character_detail

    override val viewModelClass: Class<CharacterDetailViewModel> = CharacterDetailViewModel::class.java

    //endregion

    //region Default Methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    override fun onResume() {
        super.onResume()
        viewModel.start(id = characterId)
    }

    companion object {
        fun newInstance(id: Long) = CharacterDetailFragment(id)
    }

    //endregion

    //region Methods

    override fun initViewModelObserver() {
        super.initViewModelObserver()
        viewModel.mItem.observe(this, Observer {
            requireActivity().title = it.name
            fragment_character_detail_cover_imageview?.let { imgView ->
                Picasso
                    .get()
                    .load(it.image)
                    .resize(imgView.width, imgView.height)
                    .centerCrop()
                    .transform(BlurTransformation(requireContext(), 25, 1))
                    .into(imgView)
            }
            fragment_character_detail_profil_imageview?.let { imgView ->
                Picasso
                    .get()
                    .load(it.image)
                    .into(imgView)
            }
            fragment_character_detail_name_textview?.text = it.name
            fragment_character_detail_status_textview?.text = it.status
            fragment_character_detail_specie_textview?.text = it.species
            fragment_character_detail_gender_textview?.text = it.gender
            fragment_character_detail_type_textview?.text = it.type
            fragment_character_detail_origin_textview?.text = it.origin.name
            fragment_character_detail_location_textview?.text = it.location.name
            fragment_charactersd_detail_recyclerview?.adapter?.notifyDataSetChanged()
        })
    }

    override fun startLoading() {
        fragment_characters_detail_loading_progressbar.visibility = View.VISIBLE
        //  TODO group invisible
    }

    override fun stopLoading() {
        fragment_characters_detail_loading_progressbar.visibility = View.GONE
        //  TODO group visible
    }

    override fun onError(err: Throwable) {

    }

    //endregion

}
