package com.tastyelectrons.firestore

import com.google.cloud.firestore.QueryDocumentSnapshot

actual class QueryDocumentSnapshot(queryDocumentSnapshot: QueryDocumentSnapshot): DocumentSnapshot(queryDocumentSnapshot)