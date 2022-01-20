#version 150

#moj_import <fog.glsl>

// [VanillaCopy] rendertype_entity_translucent.fsh, changes noted

uniform sampler2D Sampler0;

uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;

in float vertexDistance;
in vec4 vertexColor;
in vec4 lightMapColor;
in vec4 overlayColor;
in vec2 texCoord0;
in vec4 normal;

out vec4 fragColor;

uniform float GrainIntensity;
uniform float glow_intensity = 1;
uniform float glow_threshold = .5;
uniform float glow_size = .5;
uniform vec3 glow_colour = vec3(0, 0, 0);

float rand(vec2 co) {
    return fract(sin(dot(co.xy, vec2(12.9898,78.233))) * 43758.5453);
}

void main() {
    vec4 color = texture(Sampler0, texCoord0);
    if (color.a < 0.1) {
        discard;
    }
    color *= vertexColor * ColorModulator;
    color.rgb = mix(overlayColor.rgb, color.rgb, overlayColor.a);
    color *= lightMapColor;

    float r = rand(texCoord0);
    vec3 offset = GrainIntensity * vec3(r, r, r);
    float gs = dot(color.rgb, vec3(0.299, 0.587, 0.114));
    color = vec4(vec3(gs, gs, gs) + offset, 100);

    fragColor = linear_fog(color, vertexDistance, FogStart, FogEnd, FogColor);

}