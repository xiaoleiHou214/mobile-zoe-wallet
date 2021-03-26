package com.zoecasheth.token.entity;

import org.xml.sax.SAXException;

/**
 * Created by JB on 27/07/2020.
 */
public class TSOrigins
{
    private com.zoecasheth.token.entity.TSOriginType type;
    private String originName;
    private com.zoecasheth.token.entity.EventDefinition event;

    public static class Builder
    {
        private com.zoecasheth.token.entity.TSOriginType type;
        private String originName;
        private com.zoecasheth.token.entity.EventDefinition ev;

        public Builder(com.zoecasheth.token.entity.TSOriginType type)
        {
            this.type = type;
            this.ev = null;
        }

        public Builder name(String name)
        {
            this.originName = name;
            return this;
        }

        public Builder event(com.zoecasheth.token.entity.EventDefinition event)
        {
            this.ev = event;
            return this;
        }

        public TSOrigins build() throws SAXException
        {
            TSOrigins origins = new TSOrigins();
            origins.type = this.type;
            if (originName == null) throw new SAXException("Origins must have contract or type field.");
            origins.originName = this.originName;
            if (type == com.zoecasheth.token.entity.TSOriginType.Event && ev == null)
            {
                throw new SAXException("Event origin must have Filter spec.");
            }

            origins.event = this.ev;

            return origins;
        }
    }

    private TSOrigins()
    {

    }

    public String getOriginName()
    {
        return originName;
    }

    public EventDefinition getOriginEvent()
    {
        return event;
    }

    public boolean isType(TSOriginType checkType)
    {
        return type == checkType;
    }
}
