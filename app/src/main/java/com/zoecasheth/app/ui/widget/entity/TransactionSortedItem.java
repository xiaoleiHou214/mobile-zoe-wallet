package com.zoecasheth.app.ui.widget.entity;

import com.zoecasheth.app.entity.ActivityMeta;
import com.zoecasheth.app.entity.TransactionMeta;
import com.zoecasheth.app.ui.widget.holder.EventHolder;
import com.zoecasheth.app.ui.widget.holder.TransactionHolder;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TransactionSortedItem extends TimestampSortedItem<TransactionMeta> {

    public TransactionSortedItem(int viewType, TransactionMeta value, int order) {
        super(viewType, value, 0, order);
    }

    @Override
    public int compare(SortedItem other)
    {
        if (other.tags.contains(IS_TIMESTAMP_TAG))
        {
            TimestampSortedItem otherTimestamp = (TimestampSortedItem) other;

            if (other.value instanceof ActivityMeta)
            {
                ActivityMeta otherMeta = (ActivityMeta) other.value;
                if (other.viewType == TransactionHolder.VIEW_TYPE && otherMeta.hash.equals(value.hash) //the pending tx time will be different from the written tx time
                        && value.isPending != ((TransactionMeta)otherMeta).isPending)
                {
                    return 0; //if comparing the same tx hash with a different time, they are the same
                }

                // Check if this is a written block replacing a pending block
                if (value.hash.equals(otherMeta.hash) && otherMeta.getTimeStamp() == value.getTimeStamp()) return 0; // match

                //we were getting an instance where two transactions went through on the same
                //block - so the timestamp was the same. The display flickered between the two transactions.
                if (this.getTimestamp().equals(otherTimestamp.getTimestamp()))
                {
                    return value.hash.compareTo(otherMeta.hash);
                }
                else
                {
                    return super.compare(other);
                }
            }
            else
            {
                return super.compare(other);
            }
        }
        else
        {
            return super.compare(other);
        }
    }

    @Override
    public boolean areContentsTheSame(SortedItem other) {
        try
        {
            if (viewType == other.viewType)
            {
                TransactionMeta newTx = (TransactionMeta) other.value;
                return value.isPending == newTx.isPending;
            }
            else if (other.viewType == EventHolder.VIEW_TYPE)
            {
                return false;
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean areItemsTheSame(SortedItem other)
    {
        try
        {
            if (viewType == other.viewType)
            {
                TransactionMeta oldTx = (TransactionMeta) other.value;
                return value.hash.equals(oldTx.hash);
            }
            else if (other.viewType == EventHolder.VIEW_TYPE)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public Date getTimestamp() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTimeInMillis(value.getTimeStamp());
        return calendar.getTime();
    }
}