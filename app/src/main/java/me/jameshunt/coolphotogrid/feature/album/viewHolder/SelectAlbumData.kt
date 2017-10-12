package me.jameshunt.coolphotogrid.feature.album.viewHolder

import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract
import me.jameshunt.coolphotogrid.feature.rx.RxCommunicatorContract
import me.jameshunt.coolphotogrid.feature.rx.data.RxAlbumData
import me.jameshunt.coolphotogrid.repo.realm.RealmCollection

/**
 * Created by James on 10/9/2017.
 */
class SelectAlbumData(
        val collection: RealmCollection
) : AdapterContract.ViewHolderData