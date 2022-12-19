package todolist

object CollectionUtils:

    def removeElementByIndex[A](seq: Seq[A], index: Int): Seq[A] =
        if index < 0 || index >= seq.length then
            seq
        else if index == 0 then
            seq.tail
        else
            val (a, b) = seq.splitAt(index)
            a ++ b.tail

