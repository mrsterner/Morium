package dev.mrsterner.alchrimea.common.util;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class QuadHelper {
    public double x;
    public double y;
    public double z;
    public double angle;

    public QuadHelper(double ang, double xx, double yy, double zz) {
        this.x = xx;
        this.y = yy;
        this.z = zz;
        this.angle = ang;
    }

    public static QuadHelper setAxis(Vec3d vec, double angle) {
        angle *= 0.5D;
        double d4 = (double) MathHelper.sin((float) angle);
        return new QuadHelper((double) MathHelper.cos((float) angle), vec.getX() * d4, vec.getY() * d4, vec.getZ() * d4);
    }

    public Vec3d rotate(Vec3d vec) {
        double d = -this.x * vec.getX() - this.y * vec.getY() - this.z * vec.getZ();
        double d1 = this.angle * vec.getX() + this.y * vec.getZ() - this.z * vec.getY();
        double d2 = this.angle * vec.getY() - this.x * vec.getZ() + this.z * vec.getX();
        double d3 = this.angle * vec.getZ() + this.x * vec.getY() - this.y * vec.getX();
        double vx = d1 * this.angle - d * this.x - d2 * this.z + d3 * this.y;
        double vy = d2 * this.angle - d * this.y + d1 * this.z - d3 * this.x;
        double vz = d3 * this.angle - d * this.z - d1 * this.y + d2 * this.x;
        return new Vec3d(vx,vy,vz);
    }
}
