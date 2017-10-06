package me.jameshunt.coolphotogrid.feature.browse.viewHolder.util

import io.realm.Realm
import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract
import me.jameshunt.coolphotogrid.repo.realm.RealmPhoto

/**
 * Created by James on 10/6/2017.
 */
class GridData(
        val photos: List<RealmPhoto?>
): AdapterContract.ViewHolderData