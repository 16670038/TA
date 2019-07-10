package com.tugasakhir.dodik.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.tugasakhir.dodik.BuildConfig
import com.tugasakhir.dodik.R
import com.tugasakhir.dodik.model.ModelDataItem
import com.tugasakhir.dodik.rest.ApiClient
import com.tugasakhir.dodik.rest.ApiService
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.profile_fragment.view.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

@Suppress("UNCHECKED_CAST")
class ProfileFragment : Fragment() {
    private var items: ArrayList<ModelDataItem> = arrayListOf()
    private lateinit var npmTexView: TextView
    private lateinit var nik: TextView
    private lateinit var almt: TextView
    private lateinit var tlhr: TextView
    private lateinit var email: TextView
    private lateinit var telp: TextView
    private lateinit var ivImageProfile: CircleImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.profile_fragment, container, false)
        ivImageProfile = view.ivImageProfile
        npmTexView = view.tvNpm
        nik = view.tvNik
        almt = view.tvAlmt
        tlhr = view.tvTlhr
        email = view.tvEmail
        telp = view.tvTelp
        npmTexView.text = "16670038"

        getDatas()

        return view;
    }

    private fun getDatas() {
        val apiService: ApiService = ApiClient.provideApi()
        apiService.getProfil()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                items.clear()
                items = it.data as ArrayList<ModelDataItem>
                for (i: Int in items.indices) {
                    nik.text = items.get(i).nik
                    almt.text = items.get(i).almt
                    tlhr.text = items.get(i).tlhr
                    email.text = items.get(i).email
                    telp.text = items.get(i).telp
                    activity?.let { it1 ->
                        Glide.with(it1).load("http://informatika.upgris.ac.id/mobile/image/" + items.get(i).foto)
                            .override(512, 512).error(R.drawable.doot).into(ivImageProfile)
                    }
                    if (BuildConfig.NPM.equals("16670038")) {
                        nik.text = items.get(i).nik
                        almt.text = items.get(i).almt
                        tlhr.text = items.get(i).tlhr
                        email.text = items.get(i).email
                        telp.text = items.get(i).telp
                        activity?.let { it1 ->
                            Glide.with(it1).load("@drawable/firman.png")
                                .override(512, 512).error(R.drawable.doot).into(ivImageProfile)
                        }
                    }
                }


            }, {
                error("Error" + it.message)
            })

    }


}
