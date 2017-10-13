package me.jameshunt.coolphotogrid.feature.page.browse.viewHolder.util

import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract
import me.jameshunt.coolphotogrid.repo.realm.RealmPhoto

/**
 * Created by James on 10/6/2017.
 */
class GridData(
        val photos: List<RealmPhoto?>
): AdapterContract.ViewHolderData