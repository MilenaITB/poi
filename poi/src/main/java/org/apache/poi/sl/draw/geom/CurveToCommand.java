/*
 *  ====================================================================
 *    Licensed to the Apache Software Foundation (ASF) under one or more
 *    contributor license agreements.  See the NOTICE file distributed with
 *    this work for additional information regarding copyright ownership.
 *    The ASF licenses this file to You under the Apache License, Version 2.0
 *    (the "License"); you may not use this file except in compliance with
 *    the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 * ====================================================================
 */

package org.apache.poi.sl.draw.geom;

import java.util.Objects;

/**
 * <p>Java class for CT_Path2DCubicBezierTo complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="CT_Path2DCubicBezierTo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="pt" type="{http://schemas.openxmlformats.org/drawingml/2006/main}CT_AdjPoint2D" maxOccurs="3" minOccurs="3"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
// @XmlAccessorType(XmlAccessType.FIELD)
// @XmlType(name = "CT_Path2DCubicBezierTo", propOrder = {"pt"})
public final class CurveToCommand implements CurveToCommandIf {

    // @XmlElement(required = true)
    private final AdjustPoint pt1 = new AdjustPoint();
    // @XmlElement(required = true)
    private final AdjustPoint pt2 = new AdjustPoint();
    // @XmlElement(required = true)
    private final AdjustPoint pt3 = new AdjustPoint();

    @Override
    public AdjustPoint getPt1() {
        return pt1;
    }

    @Override
    public void setPt1(AdjustPointIf pt1) {
        if (pt1 != null) {
            this.pt1.setX(pt1.getX());
            this.pt1.setY(pt1.getY());
        }
    }

    @Override
    public AdjustPoint getPt2() {
        return pt2;
    }

    @Override
    public void setPt2(AdjustPointIf pt2) {
        if (pt2 != null) {
            this.pt2.setX(pt2.getX());
            this.pt2.setY(pt2.getY());
        }
    }

    @Override
    public AdjustPoint getPt3() {
        return pt3;
    }

    @Override
    public void setPt3(AdjustPointIf pt3) {
        if (pt3 != null) {
            this.pt3.setX(pt3.getX());
            this.pt3.setY(pt3.getY());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurveToCommand)) return false;
        CurveToCommand that = (CurveToCommand) o;
        return Objects.equals(pt1, that.pt1) &&
                Objects.equals(pt2, that.pt2) &&
                Objects.equals(pt3, that.pt3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pt1, pt2, pt3);
    }
}
