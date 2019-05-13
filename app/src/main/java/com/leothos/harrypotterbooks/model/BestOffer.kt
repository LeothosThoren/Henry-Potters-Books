package com.leothos.harrypotterbooks.model

data class BestOffer(val bestProposal: Double, val reduction: String) {
    override fun toString(): String {
        return "${bestProposal}€"
    }
}
